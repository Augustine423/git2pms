import CrewTaskPage from "../features/dashboard/common/crewtask/pages/CrewTaskPage";
import TaskRequestPage from "../features/dashboard/common/crewtask/components/TaskRequest/TaskRequestPage"
import RequestFormPage from "../features/dashboard/common/crewtask/pages/RequestFormPage";
import ApprovedRequestList from './../features/dashboard/common/crewtask/components/ApprovedRequestList';


const crewRoute = [
  {
    path: "crew-task",
    element: <CrewTaskPage />,
  },
  {
    path: "task-request",
    element: <TaskRequestPage />,
  },
  {
    path: "request-form",
    element: <RequestFormPage />,
  },
  {
    path: "approved-form",
    element: <ApprovedRequestList />,
  },
];
export default crewRoute;