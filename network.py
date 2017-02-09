import tensorflow as tf
import tensorflow.contrib.slim as slim

height = width = 227
def evaluate_network_slim():


    color_ph = tf.placeholder(dtype=tf.float32,
                               shape=(None, height, width, 3),
                               name='color_ph')
    gray_ph = tf.placeholder(dtype=tf.float32,
                               shape=(None, height, width, 1),
                               name='gray_ph')
    gradient_ph = tf.placeholder(dtype=tf.float32,
                               shape=(None, height, width, 1),
                               name='gradient_ph')
    is_training = tf.placeholder(tf.bool, name='is_training')

    color_feature = create_network_slim(color_ph, False, is_training,'color')['fea']
    gray_feature = create_network_slim(gray_ph, False, is_training, 'gray')['fea']
    gradient_feature = create_network_slim(gradient_ph, False, is_training, 'gradient')['fea']

    anchor_feature = tf.nn.l2_normalize(color_feature, dim=1, epsilon=1e-12, name="color_fea")
    gray_feature = tf.nn.l2_normalize(gray_feature, dim=1, epsilon=1e-12, name="gray_fea")
    gradient_feature = tf.nn.l2_normalize(gradient_feature, dim=1, epsilon=1e-12, name="gradient_fea")


    return ["color_fea","gray_fea","gradient_fea"]

def create_network_slim(inputs, re_u=False, is_training=True, scope_name='color'):
    with tf.variable_scope(scope_name, regularizer=tf.nn.l2_loss):
        with slim.arg_scope([slim.convolution2d, slim.fully_connected],
                            activation_fn=tf.nn.relu,
                            weights_initializer=tf.constant_initializer(0),
                            reuse=re_u):
            conv_1 = slim.convolution2d(inputs, 96, kernel_size=11, stride=4, padding='VALID', scope='conv1')
            conv_1_pool = slim.max_pool2d(conv_1, kernel_size=3, stride=2, scope='pool1')

            conv_2_1 = slim.convolution2d(conv_1_pool, 128, kernel_size=3, stride=1, scope='conv2_1')
            conv_2_2 = slim.convolution2d(conv_2_1, 128, kernel_size=3, stride=1, scope='conv2_2')
            conv_2_pool = slim.max_pool2d(conv_2_2,kernel_size=3,stride=2,scope='pool2')

            conv_3_1 = slim.convolution2d(conv_2_pool, 128, kernel_size=3, scope='conv3_1')
            conv_3_2 = slim.convolution2d(conv_3_1, 128, kernel_size=3, scope='conv3_2')

            conv_4_1 = slim.convolution2d(conv_3_2, 128, kernel_size=3,scope='conv4_1')
            conv_4_2 = slim.convolution2d(conv_4_1, 128, kernel_size=3, scope='conv4_2')

            conv_5 = slim.convolution2d(conv_4_2, 128, kernel_size=3, stride=2, scope='conv5')
            conv_5_1x1 = slim.convolution2d(conv_5, 64, kernel_size=1, scope='conv5_1x1')

            conv_5_pool = slim.max_pool2d(conv_5_1x1, kernel_size=3, stride=2, scope='pool5')
            conv_5_pool_flat = slim.flatten(conv_5_pool, scope='flat5')

            fc6 = slim.fully_connected(conv_5_pool_flat, 1024, scope='fc6')
            fc6_dropout = slim.dropout(fc6, is_training=is_training, scope='fc6_dropout')

            fc7_preact = slim.fully_connected(fc6_dropout, 128, activation_fn=None, scope='fc7')

            return {'fea': fc7_preact}