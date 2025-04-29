// FormList.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

// フォームデータの型を定義
interface Form {
  id: string;
  formName: string;
  name: string;
}

const FormList: React.FC = () => {
  const [forms, setForms] = useState<Form[]>([]);

  console.log('FormList component rendering...');

  useEffect(() => {
    console.log('Fetching forms...');
    axios.get<Form[]>('/api/formschema') // ベースURLが適用される
      .then(response => setForms(response.data))
      .catch(error => console.error('フォーム一覧取得エラー:', error));
  }, []);

  return (
    <div>
      <h1>フォーム一覧</h1>
      {forms.length === 0 ? (
        <p>フォームが見つかりません。</p>
      ) : (
        <ul>
          {forms.map(form => (
            <li key={form.id}>
              <Link to={`/forms/${form.id}`}>{form.formName}</Link>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default FormList;