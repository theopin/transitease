import React, { useEffect, useState } from 'react';

import BusStopMap from './BusStopMap';  

import { TransportApi } from '../../../../api/TransportApi';

const BusStopArrivals: any = () => {

  const [centralMarkerLocation, setCentralMarkerLocation] = useState<{ lat: number; lon: number }| null>(null);
  const [nearbyBusStops, setNearbyBusStops] = useState<any[]>([]);


  const getUserLocation = () => {
    if (navigator.geolocation) {
      console.log("getting loc")
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const { latitude, longitude } = position.coords;
          console.log(latitude, longitude);
          setCentralMarkerLocation({ lat: latitude, lon: longitude });
        },
        (err) => {
          console.error(err.message);
          setCentralMarkerLocation({ lat: 1.3, lon: 103.8 });
        }
      );
    } else {
      console.warn("Geolocation is not supported by this browser.");
      setCentralMarkerLocation({ lat: 1.3, lon: 103.8 });
    }
  };

  const fetchNearbyBusStops = async () => {
    try {

      const endpoint = "/buses/stops/details/nearby"
      const params = {
        latitude: centralMarkerLocation?.lat, 
        longitude: centralMarkerLocation?.lon,
        distance: 800
      };

      const response = await TransportApi.makeServiceRequest(endpoint, params);

      

      console.log(response.data);
      setNearbyBusStops(response.data);
    } catch (error) {
      console.error('Error fetching data:', error);
    }
  };

  useEffect(() => {
    getUserLocation();
  }, []);

  useEffect(() => {
    if (centralMarkerLocation) {
      fetchNearbyBusStops();
    }
  }, [centralMarkerLocation]);



  return (
    <div className="container mt-5">
      

      {centralMarkerLocation && (
        <BusStopMap
          centralMarkerLocation={centralMarkerLocation}
          nearbyBusStops={nearbyBusStops}
          onCentralMarkerLocationChange={setCentralMarkerLocation}
        />
      )}
      
    </div>
  );
};

export default BusStopArrivals;