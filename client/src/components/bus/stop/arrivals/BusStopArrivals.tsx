import React, { useEffect, useState } from 'react';

import { MapContainer } from 'react-leaflet/MapContainer';
import { TileLayer } from 'react-leaflet/TileLayer';
import { Popup } from 'react-leaflet/Popup';
import { Marker } from 'react-leaflet/Marker';

import { TransportApi } from '../../../../api/TransportApi';

interface NextBusInfo {
  OriginCode: string;
  DestinationCode: string;
  EstimatedArrival: string; // Consider using Date if parsing is needed
  Latitude: string;
  Longitude: string;
  Load: keyof typeof BusLoad;
  Type: keyof typeof BusType;
  Feature: keyof typeof PublicTransportFeature;
  VisitNumber: string;
}

interface BusArrivalInfo {
  NextBus: NextBusInfo;
  NextBus2: NextBusInfo;
  NextBus3: NextBusInfo;
  Operator: keyof typeof PublicTransportOperator;
  ServiceNo: string;
}

enum PublicTransportOperator {
  SBST = 'SBS Transit',
  SMRT = 'SMRT Corporation',
  TTS = 'Tower Transit Singapore',
  GAS = 'Go Ahead Singapore',
}

enum PublicTransportFeature {
  WAB = 'Wheelchair Accessible',
}

enum BusLoad {
  SEA = 'Seats Available',
  SDA = 'Standing Available',
  LSD = 'Limited Standing',
  FB = 'Full Bus',
  UNKNOWN = 'Unknown',
}

enum BusType {
  SD = 'Single Deck',
  DD = 'Double Deck',
  BD = 'Bendy',
  UNKNOWN = 'Unknown',
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
              <th>Load</th>
              <th>Type</th>
            </tr>
          </thead>
          <tbody>
            {data.map((busArrival, index) => (
              <React.Fragment key={index}>
                <tr>
                  <td rowSpan={3}>{busArrival.ServiceNo}</td>
                  <td rowSpan={3}>{busArrival.Operator}</td>
                  <td>Next Bus</td>
                  <td>{calculateMinutesDifference(new Date(), new Date(busArrival.NextBus.EstimatedArrival))}</td>
                  <td>{busArrival.NextBus.OriginCode}</td>
                  <td>{busArrival.NextBus.DestinationCode}</td>
                  <td>{BusLoad[busArrival.NextBus.Load] || BusLoad.UNKNOWN}</td>
                  <td>{BusType[busArrival.NextBus.Type] || BusType.UNKNOWN}</td>
                </tr>
                <tr>
                  <td>2Next Bus</td>
                  <td>{calculateMinutesDifference(new Date(), new Date(busArrival.NextBus2.EstimatedArrival))}</td>
                  <td>{busArrival.NextBus2.OriginCode}</td>
                  <td>{busArrival.NextBus2.DestinationCode}</td>

                  <td>{BusLoad[busArrival.NextBus2.Load] || BusLoad.UNKNOWN}</td>
                  <td>{BusType[busArrival.NextBus2.Type] || BusType.UNKNOWN}</td>
                </tr>
                <tr>
                  <td>3Next Bus</td>
                  <td>{calculateMinutesDifference(new Date(), new Date(busArrival.NextBus3.EstimatedArrival))}</td>
                  <td>{busArrival.NextBus3.OriginCode}</td>
                  <td>{busArrival.NextBus3.DestinationCode}</td>
                  <td>{BusLoad[busArrival.NextBus3.Load] || BusLoad.UNKNOWN}</td>
                  <td>{BusType[busArrival.NextBus3.Type] || BusType.UNKNOWN}</td>
                </tr>
              </React.Fragment>
            ))}
          </tbody>
        </table>
      ) : (
        <p>Loading data...</p>
      )}


      {data.length > 0 ? (
        <div>
          <MapContainer center={[1.34, 103.98]} zoom={30} scrollWheelZoom={true} style={{ width: '50%', height: '300px' }}>
            <TileLayer
              attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <Marker position={[1.34, 103.98]}>
              <Popup>
                <div>Bus Stop Location</div>
              </Popup>
            </Marker>
            {data.map((busArrival, index) => (
              <div>
                <Marker position={[busArrival.NextBus.Latitude, busArrival.NextBus.Longitude]}>
                  <Popup>
                    <div>Bus: {busArrival.ServiceNo}</div>
                    <div>Load: {BusLoad[busArrival.NextBus.Load] || BusLoad.UNKNOWN}</div>
                    <div>Type: {BusType[busArrival.NextBus.Type] || BusType.UNKNOWN}</div>
                  </Popup>
                </Marker>
                <Marker position={[busArrival.NextBus2.Latitude, busArrival.NextBus2.Longitude]}>
                  <Popup>
                    <div>Bus: {busArrival.ServiceNo}</div>
                    <div>Load: {BusLoad[busArrival.NextBus2.Load] || BusLoad.UNKNOWN}</div>
                    <div>Type: {BusType[busArrival.NextBus2.Type] || BusType.UNKNOWN}</div>
                  </Popup>
                </Marker>
              </div>
            ))}

          </MapContainer>
        </div>
      ) : (
        <p>Loading map data...</p>
      )}

    </div>
  );
};

export default BusStopArrivals;