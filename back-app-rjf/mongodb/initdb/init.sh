#!/bin/bash

# MongoDBの管理データベースに接続し、認証
mongosh <<EOF
db = db.getSiblingDB('admin');
db.auth('root', 'example');
EOF

# formDBデータベースに接続し、コレクションを作成
mongosh <<EOF
db = db.getSiblingDB('formdb');
db.createCollection('formdata');
db.createCollection('formschema');
EOF

# formschema.jsonから初期データを読み込み、formschemaコレクションに挿入
mongoimport --db formdb --collection formschema --file /docker-entrypoint-initdb.d/formschema.json --jsonArray
