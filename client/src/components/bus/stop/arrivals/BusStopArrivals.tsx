import React, { useEffect, useState } from 'react';


import { TransportApi } from '../../../../api/TransportApi';

interface NextBusInfo {
  OriginCode: string;
  DestinationCode: string;
  EstimatedArrival: string; // Consider using Date if parsing is needed
  Latitude: string;
  Longitude: string;
  Load: string;
  Type: string;
  VisitNumber: string;
}

interface BusArrivalInfo {
  NextBus: NextBusInfo;
  NextBus2: NextBusInfo;
  NextBus3: NextBusInfo;
  Operator: string;
  ServiceNo: string;
}
const BusStopArrivals: any = () => {
  const [data, setData] = useState<BusArrivalInfo[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const endpoint = "/buses/stops/arrivals/95019"
        const params = {};

        const response = await TransportApi.makeServiceRequest(endpoint, params);

        

        console.log(response.data.Services);
        setData(response.data.Services);
      } catch (error) {
        console.error('Error fetching data:', error);
        setError('Error fetching data. Please try again later.');
      }
    };

    fetchData();
  }, []);

  const calculateMinutesDifference = (start: Date, end: Date) => {
    const diff = Math.floor((end.getTime() - start.getTime()) / (1000 * 60));
    return diff;
  };

  return (
    <div className="container mt-5">
      {error && <p className="text-danger">{error}</p>}
      {data.length > 0 ? (
        <table>
          <thead>
            <tr>
              <th>Service Number</th>
              <th>Operator</th>
              <th>Next Bus</th>
              <th>Estimated Arrival</th>
              <th>Origin</th>
              <th>Destination</th>
              <th>Latitude</th>
              <th>Longitude</th>
              <th>Load</th>
              <th>Type</th>
              <th>Visit Number</th>
            </tr>
          </thead>
          <tbody>
            {data.map((busArrival, index) => (
              <React.Fragment key={index}>
                <tr>
                  <td rowSpan={3}>{busArrival.ServiceNo}</td>
                  <td rowSpan={3}>{busArrival.Operator}</td>
                  <td>Next Bus</td>
                  {/* <td>{busArrival.NextBus.EstimatedArrival}</td> */}
                  <td>{calculateMinutesDifference(new Date, new Date(busArrival.NextBus.EstimatedArrival))}</td>
                  <td>{busArrival.NextBus.OriginCode}</td>
                  <td>{busArrival.NextBus.DestinationCode}</td>
                  <td>{busArrival.NextBus.Latitude}</td>
                  <td>{busArrival.NextBus.Longitude}</td>
                  <td>{busArrival.NextBus.Load}</td>
                  <td>{busArrival.NextBus.Type}</td>
                  <td>{busArrival.NextBus.VisitNumber}</td>
                </tr>
                <tr>
                  <td>2Next Bus</td>
                  {/* <td>{busArrival.NextBus2.EstimatedArrival}</td> */}
                  <td>{calculateMinutesDifference(new Date, new Date(busArrival.NextBus2.EstimatedArrival))}</td>
                  <td>{busArrival.NextBus2.OriginCode}</td>
                  <td>{busArrival.NextBus2.DestinationCode}</td>
                  <td>{busArrival.NextBus2.Latitude}</td>
                  <td>{busArrival.NextBus2.Longitude}</td>
                  <td>{busArrival.NextBus2.Load}</td>
                  <td>{busArrival.NextBus2.Type}</td>
                  <td>{busArrival.NextBus2.VisitNumber}</td>
                </tr>
                <tr>
                  <td>3Next Bus</td>
                  {/* <td>{busArrival.NextBus3.EstimatedArrival}</td> */}
                  <td>{calculateMinutesDifference(new Date, new Date(busArrival.NextBus3.EstimatedArrival))}</td>
                  <td>{busArrival.NextBus3.OriginCode}</td>
                  <td>{busArrival.NextBus3.DestinationCode}</td>
                  <td>{busArrival.NextBus3.Latitude}</td>
                  <td>{busArrival.NextBus3.Longitude}</td>
                  <td>{busArrival.NextBus3.Load}</td>
                  <td>{busArrival.NextBus3.Type}</td>
                  <td>{busArrival.NextBus3.VisitNumber}</td>
                </tr>
              </React.Fragment>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Loading data...</p>
      )}
    </div>
  );
};

export default BusStopArrivals;