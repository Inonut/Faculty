import os
from functools import reduce

import numpy as np
from PIL import Image
from numpy import linalg as LA


# mpimg.imread('../resources/data/ASHIA AUNDHEKAR/ASHISH_L_4.jpg')




def loadData(path, nb_classes, nb_models, offset):
    data = []
    for cls in os.listdir(path)[0:nb_classes]:
        for model in os.listdir(os.path.join(path, cls))[offset:nb_models]:

            img = Image.open(os.path.join(path, cls, model))
            rsize = img.resize((200,200), Image.BICUBIC)
            data.append({
                'name': model,
                'data': np.array(rsize).astype(int)
            })
    return data



nb_classes = 5
nb_models = 3
nb_test = 5
path = "../resources/data"


images = loadData(path, nb_classes, nb_models, 0)

##############################TRAIN##################################

#I barat #### m
I_barat = reduce(lambda arr1, arr2: arr1 + arr2, map(lambda img: img['data'], images)) * 1/len(images)

#I centrat ######
for img in images:
    img['data-centrat'] = img['data'] - I_barat
    img['data-centrat-vector'] = img['data-centrat'].reshape(-1)


# A
A = []
for img in images:
    A.append(img['data-centrat-vector'])
A = np.array(A).T


# S ####
S = np.dot(A.T, A) * 1/len(images)


#eig
eig_val_sc, eig_vec_sc = np.linalg.eig(S)


# sort desc
eig_pairs = [(np.abs(eig_val_sc[i]), eig_vec_sc[:,i]) for i in range(len(eig_val_sc))]

eig_pairs.sort(key=lambda x: x[0], reverse=True)

# eig_vec_sorted = np.array(list(map(lambda elem: elem[1], eig_pairs)))


#V
V = []
for pair in eig_pairs:
    if float(pair[0]) > 1 :
        V.append(pair[1])
V = np.array(V).T


#W ###### Eigenfaces
W = np.dot(A, V)


for img in images:
    img['ProjectedImages'] = np.dot(W.T, img['data-centrat-vector'])

###########################TEST##############################


imgTestJpg = Image.open(os.path.join(path, 'NITIN NARWADE', 'NITIN_R_11.jpg'))
rsizeTest = imgTestJpg.resize((200,200), Image.BICUBIC)

imgTest = {
    'name': 'ASHISH_L_4.jpg',
    'data': np.array(rsizeTest).astype(int)
}
imgTest['data-centrat'] = imgTest['data'] - I_barat
imgTest['data-centrat-vector'] = imgTest['data-centrat'].reshape(-1)
imgTest['ProjectedImages'] = np.dot(W.T, imgTest['data-centrat-vector'])


result = sorted(images, key = lambda image: np.math.pow(LA.norm(image['ProjectedImages']) - LA.norm(imgTest['ProjectedImages']), 2))

print(result[0]['name'])




####################################
# #I barat
# I_barat = reduce(lambda arr1, arr2: arr1 + arr2, map(lambda img: img['data'], images)) * 1/len(images)
#
# #I centrat
# for img in images:
#     img['data-centrat'] = img['data'] - I_barat
#     img['data-centrat-vector'] = img['data-centrat'].reshape(-1)
#
# # A
# A = []
# for img in images:
#     A.append(img['data-centrat-vector'])
# A = np.array(A).T
#
#
# # S
# S = np.dot(A, A.T) * 1/len(images)
#
# #eig
# eig_val_sc, eig_vec_sc = np.linalg.eigh(S)
#
# # sort desc
# eig_pairs = [(np.abs(eig_val_sc[i]), eig_vec_sc[:,i]) for i in range(len(eig_val_sc))]
#
# eig_pairs.sort(key=lambda x: x[0], reverse=True)
#
#
# # V
# V = []
# for pair in eig_pairs:
#     V.append(pair[1])
# V = np.array(V).T

#########################################



# print(V.shape)


# print(eig_vec_sc)


# print(S)



# print(A.shape)

# print(images[0])

# print(I_barat)

# print(images[0]['data'] + images[1]['data'])



# print(np.asarray(images[0]['data']))





# def loadImage(root, name):
#     return {
#         'name': name,
#         'img': mpimg.imread(os.path.join(root, name))
#     }

# def loadClasses(root, nb_class):



# data = []
# for root, dirs, files in os.walk("../resources/data", topdown=False):
#     for name in files:
#         if nb_class == 0:
#             break
#         else:
#             nb_class = nb_class - 1
#
#         if name.endswith('jpg'):
#             data.append({
#                 'name': name,
#                 'img': mpimg.imread(os.path.join(root, name))
#             })
#     for dir in dirs:
#         if nb_class == :
#             break
#         else:
#             nb_class = nb_class - 1
#
#
#
# I_barat = list(map(lambda elem: elem['img'], data))

#
# data = []
# for root, dirs, files in os.walk("../resources/data", topdown=False):
#     dirName = ''
#     for name in files:
#         if name.endswith('jpg'):
#             data['' + dirName].append({
#                 'name': name,
#                 'img': mpimg.imread(os.path.join(root, name))
#             })
#
#     for dir in dirs:
#         dirName = dir
#
# print(data)