import React from 'react'
import { createBrowserRouter } from "react-router-dom";
import { Navigate } from 'react-router-dom';
import dashboardRoute from './dashboardRoute';

const router=createBrowserRouter([
  {
    path:"/",
    element: <Navigate to="/dashboard" replace />, // Redirect root to /dashboard
  },
  
  ...dashboardRoute,
  

]);
export default router