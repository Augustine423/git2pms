import SeamanOverviewPage from '../features/dashboard/seaman/pages/SeamanOverviewPage'
import SeamanRegisterPage from '../features/dashboard/seaman/pages/SeamanRegisterPage'  
import SeamanSalaryPage from '../features/dashboard/seaman/pages/SeamanSalaryPage'

const seamanRoute = [
    {
        path:"seaman-overview",
        element:<SeamanOverviewPage/>
    },
    {
        path:"seaman-register",
        element:<SeamanRegisterPage/>
    },
    {
        path:"seaman-salary",
        element:<SeamanSalaryPage/>
    }
]   
export default seamanRoute