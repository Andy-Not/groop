import Boards from "./pages/Boards";
import SidebarWithHeader from "./component/nav/SidebarWithHeader";
import { useContext, useEffect } from "react";
import axios from "axios";
import { GlobalKanbanStateContext } from "./store/KanbanContext";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import PrivateRouteLogin from "./privateRoutes/PrivateRouteLogin";
function App() {
  const [globalState, setGlobalState] = useContext(GlobalKanbanStateContext);

  // useEffect(() => {
  //   console.log("use effect ran call was made");
  //   const kanbanArr = [];
  //
  //   axios.get("api/kanban/getAllKanban").then((e) => {
  //     e.data.forEach((e) => {
  //       kanbanArr.push(e);
  //     });
  //     setGlobalState(kanbanArr);
  //   });
  // }, [setGlobalState]);
  // console.log(globalState);
  return (
    <>
      <Routes>
        <Route path={"/"} element={<Home />} />
        <Route path={"/login"} element={<Login />} />
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
