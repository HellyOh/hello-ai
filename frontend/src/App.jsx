import { useState } from "react";

export default function App() {
  const [data, setData] = useState(null);

  const callApi = async () => {
    const res = await fetch("/api/hello");   // 주소 앞에 서버명 없이 /api 로 시작!
    setData(await res.json());
  };

  return (
    <div style={{ padding: 40 }}>
      <h1>Hello AI on Docker</h1>
      <button onClick={callApi}>AI 호출하기</button>
      <pre>{data && JSON.stringify(data, null, 2)}</pre>
    </div>
  );
}