import React, { useState, useEffect, useMemo, useRef } from 'react';
import { TransportApi } from '../../../../api/TransportApi';


import {Icon} from 'leaflet';

import { MapContainer } from 'react-leaflet/MapContainer';
import { TileLayer } from 'react-leaflet/TileLayer';
import { Popup } from 'react-leaflet/Popup';
import { Marker } from 'react-leaflet/Marker';
import { Circle } from 'react-leaflet/Circle';

const BusStopMap: any  = ({ centralMarkerLocation, nearbyBusStops, onCentralMarkerLocationChange }) => {
    const [busStopNumber, setBusStopNumber] = useState<any| null>(null);
    const [busStopArrivals, setBusStopArrivals] = useState<any| null>(null);

    const markerRef = useRef(null)

    const fetchBusesArrivingAtBusStop = async () => {
      try {
  
        const endpoint = `/buses/stops/arrivals/${busStopNumber}`
        const params = {
          latitude: centralMarkerLocation?.lat, 
          longitude: centralMarkerLocation?.lon,
          distance: 800
        };
  
        const response = await TransportApi.makeServiceRequest(endpoint, params);
  
        
  
        console.log(response.data.Services);
        setBusStopArrivals(response.data.Services);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
  

    useEffect(() => {
      if (busStopNumber) {
        fetchBusesArrivingAtBusStop();
      }
    }, [busStopNumber]);

    const centralMarkerIcon = new Icon({
        iconUrl: 'https://img.icons8.com/?size=100&id=ziC1TV1ikKi9&format=png&color=A52A2A',
        iconSize: [35, 35],
        // iconAnchor: [22, 94],
      });
    
    const busStopMarkerIcon = new Icon({
      iconUrl: 'https://img.icons8.com/?size=100&id=117119&format=png&color=000000',
      iconSize: [24, 24],
      iconAnchor: [0, 0],
      popupAnchor: [12,5],
    });


    const onCentralMarkerDrag = useMemo(
      () => ({
        dragend() {
          const marker = markerRef.current
          if (marker != null) {
              onCentralMarkerLocationChange({ lat: marker.getLatLng().lat, lon: marker.getLatLng().lng})
          }
        },
      }),
      [],
    )

    const onBusStopMarkerClick = (busStopCode) => () => {
        console.log("Bus Stop Code:", busStopCode);
        setBusStopNumber(busStopCode)
    };

    const calculateMinutesDifference = (start: Date, end: Date) => {
      const diff = Math.floor((end.getTime() - start.getTime()) / (1000 * 60));

      if (Number.isNaN(diff)) {
        return "N/A"
      }
      return diff;
    };
      
      return (
        <MapContainer center={[centralMarkerLocation.lat, centralMarkerLocation.lon]} zoom={16} scrollWheelZoom={true} style={{ width: '50%', height: '300px' }}>
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
    
          <Marker
            position={centralMarkerLocation}
            draggable={true}
            eventHandlers={onCentralMarkerDrag}
            icon={centralMarkerIcon}
            ref={markerRef}
          />
          <Circle center={centralMarkerLocation} radius={800} />
    
          {nearbyBusStops.map((busStopDetails) => (
            <Marker
              position={[busStopDetails.Latitude, busStopDetails.Longitude]}
              eventHandlers={{ click: onBusStopMarkerClick(busStopDetails.BusStopCode) }}
              key={busStopDetails.BusStopCode}
              icon={busStopMarkerIcon}
            >
              <Popup>
                <div>{busStopDetails.Description}</div>
                <div>{busStopDetails.RoadName}</div>
                {busStopArrivals && 
                  busStopArrivals.map((busService) => (
                    <div key={busService.ServiceNo} className="row text-center">
                      <div className="col-sm-3 badge text-bg-success">{busService.ServiceNo}</div>
                      <div className="col-sm-2">{calculateMinutesDifference(new Date(), new Date(busService.NextBus.EstimatedArrival))}</div>
                      <div className="col-sm-2">{calculateMinutesDifference(new Date(), new Date(busService.NextBus2.EstimatedArrival))}</div>
                      <div className="col-sm-2">{calculateMinutesDifference(new Date(), new Date(busService.NextBus3.EstimatedArrival))}</div>
                    </div>
                  ))
                }
              </Popup>
            </Marker>
          ))}

        </MapContainer>

      );
}

export default BusStopMap;