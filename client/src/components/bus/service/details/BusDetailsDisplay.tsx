import React, { useEffect, useState } from 'react';


import { TransportApi } from '../../../../api/TransportApi';

interface BusService {
  ServiceNo: string;
  Operator: string;
  Direction: number;
  Category: string;
  OriginCode: string;
  DestinationCode: string;
  AM_Peak_Freq: string;
  AM_Offpeak_Freq: string;
  PM_Peak_Freq: string;
  PM_Offpeak_Freq: string;
  LoopDesc: string;
}

const BusDetailsDisplay: any = () => {
  const [data, setData] = useState<BusService[]>([]);
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
    <div className="container mt-5">
      {error && <p className="text-danger">{error}</p>}
      {data.length > 0 ? (
        <table className="table table-striped table-bordered">
          <thead className="thead-dark">
            <tr>
              <th>ServiceNo</th>
              <th>Operator</th>
              <th>Direction</th>
              <th>Category</th>
              <th>OriginCode</th>
              <th>DestinationCode</th>
              <th>AM Peak Frequency</th>
              <th>AM Offpeak Frequency</th>
              <th>PM Peak Frequency</th>
              <th>PM Offpeak Frequency</th>
              <th>Loop Description</th>
            </tr>
          </thead>
          <tbody>
            {data.map((item, index) => (
              <tr key={index}>
                <td>{item.ServiceNo}</td>
                <td>{item.Operator}</td>
                <td>{item.Direction}</td>
                <td>{item.Category}</td>
                <td>{item.OriginCode}</td>
                <td>{item.DestinationCode}</td>
                <td>{item.AM_Peak_Freq}</td>
                <td>{item.AM_Offpeak_Freq}</td>
                <td>{item.PM_Peak_Freq}</td>
                <td>{item.PM_Offpeak_Freq}</td>
                <td>{item.LoopDesc}</td>
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

export default BusDetailsDisplay;