import LeaderTaskPage from "../features/dashboard/common/leader/Pages/LeaderTaskPage";
import ApprovalListPage from "../features/dashboard/common/leader/Pages/ApprovalListPage";
import AssignTaskPage from "../features/dashboard/common/leader/Pages/AssignTaskPage";
import DocumentPage from "../features/dashboard/common/leader/Pages/DocumentPage";

const  leaderRoute = [
    {
        path:"leader-task",
        element:<LeaderTaskPage/>
    },
    {
        path:"approval-list",
        element:<ApprovalListPage/>
    },
    {
        path:"assign-task",
        element:<AssignTaskPage/>
    },
    {
        path:"document",
        element:<DocumentPage/>
    }
]
export default leaderRoute;