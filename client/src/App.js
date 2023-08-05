import Boards from "./pages/Boards";
import SidebarWithHeader from "./component/nav/SidebarWithHeader";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import PrivateRouteLogin from "./privateRoutes/PrivateRouteLogin";
import SignUp from "./pages/SignUp";
import { useContext, useEffect } from "react";
import { useLocalStorage } from "./hooks/useLocalStorage";
import axios from "axios";
import { GlobalKanbanStateContext } from "./store/KanbanContext";
function App() {
  const [globalState, setGlobalState] = useContext(GlobalKanbanStateContext);
  const [jwt] = useLocalStorage("", "jwt");
  useEffect(() => {
    if (jwt !== "") {
      axios.get(`/api/user/findUser?token=${jwt}`).then((res) => {
        console.log("called in app");
        if (res.status === 200) {
          try {
            axios
              .get(`/api/kanban/getAllKanban?userID=${res.data.id}`)
              .then((res2) => {
                const allKanbans = [];
                res2.data.map((kanban) => {
                  allKanbans.push(kanban);
                });
                setGlobalState(allKanbans);
              });
          } catch (e) {
            console.log(e);
          }
        }
      });
    }
  }, [jwt]);
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
