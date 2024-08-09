import React from 'react';
import BusDetailsDisplay from './components/bus/service/details/BusDetailsDisplay'
import BusServiceRoutes from './components/bus/service/routes/BusServiceRoutes'
import BusStopDetails from './components/bus/stop/details/BusStopDetails'
import BusStopArrivals from './components/bus/stop/arrivals/BusStopArrivals'

function App() {
  return (
    <div>
      <h1>Transport API Example</h1>
      {/* <BusDetailsDisplay /> */}
      {/* <BusServiceRoutes /> */}
      <BusStopArrivals></BusStopArrivals>
      {/* <BusStopDetails></BusStopDetails> */}
    </div>
  );
}

export default App;
