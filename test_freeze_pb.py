import tensorflow as tf
from tensorflow.python.framework import graph_util
from tensorflow.contrib.slim.python.slim.nets import alexnet
from tensorflow.python.ops import random_ops
from tensorflow.python.tools import optimize_for_inference_lib
from tensorflow.python.framework import dtypes
from tensorflow.core.framework import graph_pb2
import tensorflow.contrib.slim as slim
import network
import os
batch_size = 128
height = width = 224
num_classes = 1000

def save_model(model_path):

    input = tf.placeholder(tf.float32,(None,height,width,3),'input_tensor')
    logits, _ = alexnet.alexnet_v2(input, num_classes)
    output_tensor = tf.identity(logits,name='output_tensor')
    with tf.Session() as sess:
        sess.run(tf.global_variables_initializer())
        saver = tf.train.Saver()
        chkp = saver.save(sess, model_path)
        print( 'Save to ' + chkp )


def freeze_model_single(model_path):
    # We retrieve our checkpoint fullpath
    checkpoint = tf.train.get_checkpoint_state(model_path)
    input_checkpoint = checkpoint.model_checkpoint_path

    # We precise the file fullname of our freezed graph
    # absolute_model_folder = "/".join(input_checkpoint.split('/')[:-1])
    # output_graph = absolute_model_folder + "/frozen_model.pb"
    output_graph = 'model/' + 'frozen_model.pb'

    # 'output_tensor1,output_tensor2,...'
    output_node_names = "anr".split(",")

    saver = tf.train.import_meta_graph(input_checkpoint + '.meta', clear_devices=True)
    input_graph_def = tf.get_default_graph().as_graph_def()

    with tf.Session() as sess:
        saver.restore(sess, input_checkpoint)

        output_graph_def = graph_util.convert_variables_to_constants(
            sess,  # The session is used to retrieve the weights
            input_graph_def,  # The graph_def is used to retrieve the nodes
            output_node_names  # The output node names are used to select the usefull nodes
        )

        with tf.gfile.FastGFile(output_graph, "w") as f:
            f.write(output_graph_def.SerializeToString())
        print("%d ops in the final graph." % len(output_graph_def.node))

def freeze_model(model_path):

    # We retrieve our checkpoint fullpath
    checkpoint = tf.train.get_checkpoint_state(model_path)
    input_checkpoint = checkpoint.model_checkpoint_path

    # We precise the file fullname of our freezed graph
    output_graph = 'model/'+ 'frozen_model.pb'

    # 'output_tensor1,output_tensor2,...'
    output_node_names = network.evaluate_network_slim()
    input_graph_def = tf.get_default_graph().as_graph_def()

    def name_in_checkpoint(var, type):
        if type in var.op.name:
            return var.op.name.replace(type, "student")

    vars_to_restore = slim.get_model_variables()

    vars_color_to_restore = {name_in_checkpoint(var,'color'): var for var in vars_to_restore}

    color_restorer = tf.train.Saver(vars_color_to_restore)

    with tf.Session() as sess:
        color_restorer.restore(sess, input_checkpoint)


        output_graph_def = graph_util.convert_variables_to_constants(
            sess,  # The session is used to retrieve the weights
            input_graph_def,  # The graph_def is used to retrieve the nodes
            output_node_names  # The output node names are used to select the usefull nodes
        )

        with tf.gfile.FastGFile(output_graph, "w") as f:
            f.write(output_graph_def.SerializeToString())
        print("%d ops in the final graph." % len(output_graph_def.node))

def optimize_model(model_path):
    if not tf.gfile.Exists(model_path):
        return -1

    input_graph_def = graph_pb2.GraphDef()
    with tf.gfile.Open(model_path, "rb") as f:
        data = f.read()
        input_graph_def.ParseFromString(data)


    output_graph_def = optimize_for_inference_lib.optimize_for_inference(
        input_graph_def,
        'input_tensor'.split(','),
        'output_tensor'.split(','), dtypes.float32.as_datatype_enum)

    with tf.gfile.FastGFile('model/optimzed_model.pb','w') as f:
        f.write(output_graph_def.SerializeToString())


def main(_):
    os.environ['CUDA_VISIBLE_DEVICES'] = '1'


    #save_model('model/MDP.chkp')

    freeze_model( '/home/xiao/projects/tensoflow_pillrec/model_alexnet/color_0')

    #optimize_model('model/frozen_model.pb')




if __name__ == '__main__':

    tf.app.run()