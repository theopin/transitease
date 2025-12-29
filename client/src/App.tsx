import React from 'react';
import BusDetailsDisplay from './components/bus/service/details/BusDetailsDisplay'
import BusServiceRoutes from './components/bus/service/routes/BusServiceRoutes'
import BusStopDetails from './components/bus/stop/details/BusStopDetails'
import BusStopArrivals from './components/bus/stop/arrivals/BusStopArrivals'
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom';

function App() {
  return (
    <div>
      <h1>Transport API Example</h1>
      <BrowserRouter>
          <Routes>
            {/* Default route redirects to BusStopArrivals */}
            <Route path="/" element={<Navigate to="/bus/arrivals" replace />} />
            <Route path="/bus/details/:service" element={<BusDetailsDisplay />} />
            <Route path="/bus/routes/:service" element={<BusServiceRoutes />} />
            <Route path="/bus/arrivals" element={<BusStopArrivals />} />
            <Route path="/bus/stops/:code" element={<BusStopDetails />} />
          </Routes>
      </BrowserRouter>

    </div>
  );
}

export default App;
