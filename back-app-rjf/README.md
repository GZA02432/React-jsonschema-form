# back-app-rjf

## 概要

`back-app-rjf` は、[react-jsonschema-form](https://github.com/rjsf-team/react-jsonschema-form) の JSON データを管理するためのバックエンドアプリケーションです。

## 特徴

1. JSON データは MongoDB を使用して管理します。
2. MongoDB は Docker を使用して起動し、初期データとして JSON Schema が登録されます。
3. アプリケーションは Spring Boot と Java 17 を使用して実行されます。
4. MongoDB のデータは `localhost:8081` で確認できます。

---

## セットアップ手順

### 必要な環境

- Docker および Docker Compose
- Java 17
- Gradle

### 1. MongoDB の起動

以下のコマンドを実行して MongoDB を起動します。

```sh
docker-compose up -d
```

- MongoDB は `localhost:27017` で起動します。
- MongoDB の管理ツール [mongo-express](https://github.com/mongo-express/mongo-express) が `localhost:8081` で利用可能です。

### 2. アプリケーションのビルドと実行

以下のコマンドを実行してアプリケーションをビルドし、起動します。

```sh
./gradlew bootRun
```

アプリケーションは `http://localhost:8080` で起動します。

---

## 初期データ

MongoDB の初期データとして、以下の JSON Schema が登録されます。

- `mongodb/initdb/formschema.json` に定義されたスキーマが自動的に登録されます。

---

## MongoDB のデータ確認

MongoDB のデータは以下の手順で確認できます。

1. ブラウザで `http://localhost:8081` にアクセスします。
2. ユーザー名: `root`、パスワード: `example` を使用してログインします。
3. データベース `formdb` 内のコレクション `formschema` や `formdata` を確認できます。

---

## 使用技術

- **バックエンド**: Spring Boot
- **データベース**: MongoDB
- **言語**: Java 17
- **ビルドツール**: Gradle
- **コンテナ**: Docker, Docker Compose

---

## ディレクトリ構成

```
back-app-rjf/
├── src/                     # ソースコード
├── mongodb/                 # MongoDB の初期データと設定
├── build.gradle             # Gradle ビルド設定
├── docker-compose.yml       # Docker Compose 設定
└── README.md                # このファイル
```

---

## ライセンス
