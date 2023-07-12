import Boards from "./pages/Boards";
import SidebarWithHeader from "./component/nav/SidebarWithHeader";
import { useContext, useEffect } from "react";
import axios from "axios";
import { GlobalKanbanStateContext } from "./store/KanbanContext";
import Login from "./pages/Login";
import { Route, Routes } from "react-router-dom";
function App() {
  const [globalState, setGlobalState] = useContext(GlobalKanbanStateContext);

  useEffect(() => {
    console.log("use effect ran call was made");
    const kanbanArr = [];

    axios.get("api/kanban/getAllKanban").then((e) => {
      e.data.forEach((e) => {
        kanbanArr.push(e);
      });
      setGlobalState(kanbanArr);
    });
  }, [setGlobalState]);
  console.log(globalState);
  return (
    <>
      <Routes>
        <Route path={"/login"} element={<Login />} />
      </Routes>
      {/*<Login />*/}
      {/*<SidebarWithHeader>*/}
      {/*  <Boards />*/}
      {/*</SidebarWithHeader>*/}
    </>
  );
}

export default App;
