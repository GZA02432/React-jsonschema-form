#!/bin/bash

# MongoDB�̊Ǘ��f�[�^�x�[�X�ɐڑ����A�F��
mongosh <<EOF
db = db.getSiblingDB('admin');
db.auth('root', 'example');
EOF

# formDB�f�[�^�x�[�X�ɐڑ����A�R���N�V�������쐬
mongosh <<EOF
db = db.getSiblingDB('formdb');
db.createCollection('formdata');
db.createCollection('formschema');
EOF

# formschema.json���珉���f�[�^��ǂݍ��݁Aformschema�R���N�V�����ɑ}��
mongoimport --db formdb --collection formschema --file /docker-entrypoint-initdb.d/formschema.json --jsonArray
