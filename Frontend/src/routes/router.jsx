import React from 'react'
import { createBrowserRouter } from "react-router-dom";
import { Navigate } from 'react-router-dom';
import dashboardRoute from './dashboardRoute';
import publicRoute from './publicRoute';
import authRoute from './authRoute';

const router=createBrowserRouter([
  {
    path:"/",
    children: [...publicRoute], // Redirect root to /dashboard
  },
  ...authRoute,
  ...dashboardRoute,
  

]);
export default router