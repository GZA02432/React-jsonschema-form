import axios from 'axios';

// ベースURLを設定
axios.defaults.baseURL = 'http://localhost:8080'; // ドメインやポートを指定

// 必要に応じて他のデフォルト設定も追加
axios.defaults.headers.common['Content-Type'] = 'application/json';

axios.interceptors.response.use(
  response => response,
  error => {
    console.error('APIエラー:', error);
    return Promise.reject(error);
  }
);

export default axios;