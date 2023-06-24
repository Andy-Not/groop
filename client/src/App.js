import Boards from "./pages/Boards";
import SidebarWithHeader from "./component/nav/SidebarWithHeader";
import { useContext, useEffect, useState } from "react";
import axios from "axios";
import { GlobalKanbanStateContext } from "./store/KanbanContext";
function App() {
  const [columns, setColumns] = useState({});
  const [globalState, setGlobalState] = useContext(GlobalKanbanStateContext);

  useEffect(() => {
    console.log("use effect ran call was made");
    const boards = [];
    const kanbanArr = [];

    axios.get("api/kanban/getAllKanban").then((e) => {
      e.data[0].swimLanes.forEach((e) => {
        boards.push(e);
      });
      e.data.forEach((e) => {
        kanbanArr.push(e);
      });
      const groupedObjects = boards.reduce((group, obj) => {
        group[obj.id] = obj;
        return group;
      }, {});
      setColumns(groupedObjects);
      setGlobalState(kanbanArr);
    });
  }, [setGlobalState]);
  console.log(globalState);
  return (
    <>
      <SidebarWithHeader>
        <Boards setColumns={setColumns} />
      </SidebarWithHeader>
    </>
  );
}

export default App;
