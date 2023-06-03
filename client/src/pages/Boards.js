import { useEffect, useState } from "react";
import axios from "axios";
import Board from "../component/Board";
import { HStack } from "@chakra-ui/react";

const Boards = () => {
  const [kanbans, setKanbans] = useState([]);

  useEffect(() => {
    console.log("use effect ran call was made");
    const boards = [];
    axios.get("api/kanban/getAllKanban").then((e) => {
      console.log(e.data[0]);
      e.data.forEach((e) => {
        boards.push(e);
      });
      setKanbans(boards);
    });
  }, []);

  return (
    <HStack>
      {kanbans.map((e) => {
        console.log(e.tasks);
        return <Board title={e.title} tasks={e.tasks}></Board>;
      })}
    </HStack>
  );
};
export default Boards;
