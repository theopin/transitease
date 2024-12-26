import React from 'react';
import BusDetailsDisplay from './components/bus/service/details/BusDetailsDisplay'
import BusServiceRoutes from './components/bus/service/routes/BusServiceRoutes'
import BusStopDetails from './components/bus/stop/details/BusStopDetails'
import BusStopArrivals from './components/bus/stop/arrivals/BusStopArrivals'
import { BrowserRouter, Navigate, Route, Router, Routes } from 'react-router-dom';

function App() {
  return (
    <div>
      <h1>Transport API Example</h1>
      <BrowserRouter>
          <Routes>
            {/* Default route redirects to BusStopArrivals */}
            <Route path="/" element={<Navigate to="/bus/stops/arrivals" replace />} />
            <Route path="/bus/details" element={<BusDetailsDisplay />} />
            <Route path="/bus/routes" element={<BusServiceRoutes />} />
            <Route path="/bus/stops/arrivals" element={<BusStopArrivals />} />
            <Route path="/bus/stops" element={<BusStopDetails />} />
          </Routes>
      </BrowserRouter>

    </div>
  );
}

export default App;
