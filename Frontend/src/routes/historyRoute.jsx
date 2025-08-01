import CrewTaskHistory from "../features/dashboard/common/history/components/CrewTaskHistory";
import TaskHistoryPage from "../features/dashboard/common/history/Pages/TaskHistoryPage";
const historyRoute = [
  {
    path: "crew-task-history",
    element: <CrewTaskHistory />,
  },
  {
    path: "task-history",
    element: <TaskHistoryPage />,
  }
];
export default historyRoute;    