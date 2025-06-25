import React from 'react'
import ShipOverviewPage from '../features/dashboard/ship/pages/ShipOverviewPage'
import ShipRegisterPage from '../features/dashboard/ship/pages/ShipRegisterPage'
import ShipActivePage from '../features/dashboard/ship/pages/ShipActivePage'
import ShipInactivePage from '../features/dashboard/ship/pages/ShipInactivePage'

const shipRoute = [
    {
        path:"ship-overview",
        element:<ShipOverviewPage/>
    },
    {
        path:"ship-register",
        element:<ShipRegisterPage/>
    },
    {
        path:"ship-active",
        element:<ShipActivePage/>
    },
    {
        path:"ship-inactive",
        element:<ShipInactivePage/>
    }
]

export default shipRoute