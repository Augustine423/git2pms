import React from 'react'
import Overview from "../features/dashboard/common/Overview"
import DashboardLayout from '../features/dashboard/common/DashboardLayout';
import shipRoute from './shipRoute';
import seamanRoute from './seamanRoute';
import crewRoute from './crewRoute';
const dashboardRoute = [
  {
    path: "/dashboard",
    element: <DashboardLayout/>,
    children: [
      {
        index: true, // Redirect to RealTimeInfo after login
        element:<Overview/>
      },
      ...shipRoute,
      ...seamanRoute,
      ...crewRoute
    ],
  },
];

export default dashboardRoute