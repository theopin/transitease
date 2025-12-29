import React, { useEffect, useState } from 'react';


import { TransportApi } from '../../../../api/TransportApi';



import { BusStopInfo } from './BusStyles'; // Adjust the path as needed
import { useParams } from 'react-router-dom';


const BusStopDetails: any = () => {
  const { code } = useParams<{ code: string }>();
  const [data, setData] = useState<BusStopInfo[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
        try {
            const endpoint = `/buses/stops/details/${code}`
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
  }, [code]);

  return (
    <div className="container mt-5">
      {error && <p className="text-danger">{error}</p>}
      {data.length > 0 ? (
        <table className="table table-striped table-bordered">
          <thead>
            <tr>
              <th>Bus Stop Code</th>
              <th>Description</th>
              <th>Latitude</th>
              <th>Longitude</th>
              <th>Road Name</th>
            </tr>
          </thead>
          <tbody>
            {data.map((busStop, index) => (
              <tr key={index}>
                <td>{busStop.BusStopCode}</td>
                <td>{busStop.Description}</td>
                <td>{busStop.Latitude}</td>
                <td>{busStop.Longitude}</td>
                <td>{busStop.RoadName}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Loading data...</p>
      )}
    </div>
  );
};

export default BusStopDetails;