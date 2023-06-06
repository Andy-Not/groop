import { useContext, useEffect } from "react";
import axios from "axios";
import Board from "../component/Board";
import { HStack } from "@chakra-ui/react";
import { GlobalBoardContext } from "../stores/BoardStore";

const Boards = () => {
  const [globalBoardList, setGlobalBoardList] = useContext(GlobalBoardContext);
  useEffect(() => {
    console.log("use effect ran call was made");
    const boards = [];

    axios.get("api/kanban/getAllKanban").then((e) => {
      e.data.forEach((e) => {
        boards.push(e);
      });
      setGlobalBoardList(boards);
    });
  }, [setGlobalBoardList]);
  return (
    <HStack>
      {globalBoardList.map((e) => {
        return (
          <Board
            index={e.index}
            key={e.id}
            id={e.id}
            title={e.title}
            tasks={e.tasks}
          />
        );
      })}
    </HStack>
  );
};
export default Boards;
