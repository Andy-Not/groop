import Boards from "./pages/Boards";
import SidebarWithHeader from "./component/nav/SidebarWithHeader";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import PrivateRouteLogin from "./privateRoutes/PrivateRouteLogin";
import SignUp from "./pages/SignUp";
function App() {
  return (
    <>
      <Routes>
        <Route path={"/"} element={<Home />} />
        <Route path={"/login"} element={<Login />} />
        <Route path={"/signup"} element={<SignUp />} />
        <Route
          path={"/dashboard"}
          element={
            <PrivateRouteLogin>
              <SidebarWithHeader>
                <Boards />
              </SidebarWithHeader>
            </PrivateRouteLogin>
          }
        />
      </Routes>
      {/*<Login />*/}
    </>
  );
}

export default App;
