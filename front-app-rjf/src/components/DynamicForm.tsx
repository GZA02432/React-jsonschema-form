import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Form, { IChangeEvent } from '@rjsf/core'; // デフォルトテーマを使用
import { useNavigate, useParams } from 'react-router-dom';
import validator from '@rjsf/validator-ajv8'; // バリデータをインポート
import { JSONSchema7 } from 'json-schema';

interface SchemaResponse {
  schema: JSONSchema7;
  uiSchema: JSONSchema7;
}

const DynamicForm: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [schema, setSchema] = useState<object | null>(null);
  const [uiSchema, setUiSchema] = useState<object>({});
  const [formData, setFormData] = useState<object>({});
  const navigate = useNavigate();

  useEffect(() => {
    const fetchSchema = async () => {
      try {
        const response = await axios.get<SchemaResponse>(`/api/formschema/${id}/schema-and-ui`);
        setSchema(response.data.schema);
        setUiSchema(response.data.uiSchema || {});
      } catch (error) {
        console.error('スキーマ取得エラー:', error);
      }
    };
    fetchSchema();
  }, [id]);

  if (!schema) {
    return <div>フォームをロード中...</div>;
  }

  const handleSubmit = (event: IChangeEvent<object>) => {
    const payload = {
      formSchemaId: id, // formSchemaIdにURLパラメータのidを設定
      formData: event.formData || {}, // formDataをそのまま使用
    };

    axios.post('/api/formdata/submit', payload)
      .then(() => {
        alert('フォームが送信されました！');
        navigate('/');
      })
      .catch(error => console.error('フォーム送信エラー:', error));
  };

  const handleChange = (event: IChangeEvent<object>) => {
    setFormData(event.formData || {});
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center">フォーム入力</h1>
      <Form
        schema={schema}
        uiSchema={uiSchema}
        formData={formData}
        onChange={handleChange}
        onSubmit={handleSubmit}
        validator={validator}
      />
    </div>
  );
};

export default DynamicForm;