import PageLoading from "../common/PageLoading";
import LoginPage from "../features/auth/pages/LoginFormPage";
import { Suspense } from "react";

const authRoute = [
  { 
    path: "/login",
    element: (
        <Suspense fallback={<PageLoading />}>
            <LoginPage />
        </Suspense>
    ),
  },
];
export default authRoute;