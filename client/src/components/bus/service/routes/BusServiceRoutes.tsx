import React, { useEffect, useState } from 'react';


import { TransportApi } from '../../../../api/TransportApi';

interface BusRouteStopInfo {
  BusStopCode: string;
  Direction: number;
  Distance: number;
  Operator: string;
  SAT_FirstBus: string;
  SAT_LastBus: string;
  SUN_FirstBus: string;
  SUN_LastBus: string;
  ServiceNo: string;
  StopSequence: number;
  WD_FirstBus: string;
  WD_LastBus: string;
}

const BusServiceRoutes: any = () => {
  const [data, setData] = useState<BusRouteStopInfo[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
        try {
            const endpoint = "/buses/services/routes/66"
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
          <thead>
              <tr>
                <th>Bus Stop Code</th>
                <th>Direction</th>
                <th>Distance</th>
                <th>Operator</th>
                <th>Service Number</th>
                <th>Stop Sequence</th>
                <th>SAT First Bus</th>
                <th>SAT Last Bus</th>
                <th>SUN First Bus</th>
                <th>SUN Last Bus</th>
                <th>WD First Bus</th>
                <th>WD Last Bus</th>
              </tr>
          </thead>
          <tbody>
              {data.map((busRouteStop, index) => (
                <tr key={index}>
                  <td>{busRouteStop.BusStopCode}</td>
                  <td>{busRouteStop.Direction}</td>
                  <td>{busRouteStop.Distance}</td>
                  <td>{busRouteStop.Operator}</td>
                  <td>{busRouteStop.ServiceNo}</td>
                  <td>{busRouteStop.StopSequence}</td>
                  <td>{busRouteStop.SAT_FirstBus}</td>
                  <td>{busRouteStop.SAT_LastBus}</td>
                  <td>{busRouteStop.SUN_FirstBus}</td>
                  <td>{busRouteStop.SUN_LastBus}</td>
                  <td>{busRouteStop.WD_FirstBus}</td>
                  <td>{busRouteStop.WD_LastBus}</td>
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

export default BusServiceRoutes;