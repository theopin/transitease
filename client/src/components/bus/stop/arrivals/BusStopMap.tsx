import React, {useMemo, useRef } from 'react';


import {Icon} from 'leaflet';

import { MapContainer } from 'react-leaflet/MapContainer';
import { TileLayer } from 'react-leaflet/TileLayer';
import { Popup } from 'react-leaflet/Popup';
import { Marker } from 'react-leaflet/Marker';


const BusStopMap: any  = ({ centralMarkerLocation, nearbyBusStops, onCentralMarkerLocationChange }) => {

    const markerRef = useRef(null)


    const centralMarkerIcon = new Icon({
        iconUrl: 'https://img.icons8.com/doodle/48/heart-with-pulse.png',
        iconSize: [35, 35],
        iconAnchor: [22, 94],
        popupAnchor: [-3, -76],
      });
    
      const busStopMarkerIcon = new Icon({
        iconUrl: 'https://img.icons8.com/?size=100&id=54316&format=png&color=000000',
        iconSize: [24, 24],
        iconAnchor: [0, 0],
        popupAnchor: [-3, -76],
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
    
          {nearbyBusStops.map((busStopDetails) => (
            <Marker
              position={[busStopDetails.Latitude, busStopDetails.Longitude]}
              key={busStopDetails.BusStopCode}
              icon={busStopMarkerIcon}
            >
              <Popup>
                <div>{busStopDetails.Description}</div>
                <div>{busStopDetails.RoadName}</div>
              </Popup>
            </Marker>
          ))}

        </MapContainer>

      );
}

export default BusStopMap;