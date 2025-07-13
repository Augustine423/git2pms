import LeaderTaskPage from "../features/dashboard/common/leader/Pages/LeaderTaskPage";
import ApprovalListPage from "../features/dashboard/common/leader/Pages/ApprovalListPage";
const  leaderRoute = [
    {
        path:"leader-task",
        element:<LeaderTaskPage/>
    },
    {
        path:"approval-list",
        element:<ApprovalListPage/>
    }
]
export default leaderRoute;