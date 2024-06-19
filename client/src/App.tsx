import React, { useEffect, useState } from 'react';
import './App.css';

import { TransportApi } from './api/TransportApi'

function App() {

  const [data, setData] = useState<any>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const endpoint = "/buses/services/details/66"
        const params = {};

        const response = await TransportApi.makeServiceRequest(endpoint, params);
        console.log(response.data);
        setData(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
        setError('Error fetching data. Please try again later.');
      }
    };

    fetchData();
  }, []);


  return (
    <div className="App">
      <h1>Transport API Example</h1>
      {error && <p>{error}</p>}
      {data && (
        <pre>{JSON.stringify(data, null, 2)}</pre>
      )}
    </div>
  );
}

export default App;
