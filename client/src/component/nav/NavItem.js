import { Flex, Link } from "@chakra-ui/react";
import axios from "axios";
import { useContext, useState } from "react";
import { GlobalSwimLaneStateContext } from "../../store/SwimLaneConetext";
import { useLocalStorage } from "../../hooks/useLocalStorage";
import { GlobalCurrentKanbanStateContext } from "../../store/CurrentKanbanContext";
const NavItem = ({ children, ...rest }) => {
  const [currentId, setCurrentId] = useState("");
  const [laneData, setLaneData] = useLocalStorage({}, "swimLane");
  const [currentSwimLane, setCurrentSwimLane] = useContext(
    GlobalSwimLaneStateContext
  );

  const [currentKanban, setCurrentKanban] = useContext(
    GlobalCurrentKanbanStateContext
  );
  const [currentKanban1, setCurrentKanban1] = useLocalStorage(
    currentKanban,
    "currentKanban"
  );
  const isEqual = (obj1, obj2) => {
    return JSON.stringify(obj1) === JSON.stringify(obj2);
  };

  const findSwimLanes = (kanbanId) => {
    if (isEqual(laneData, currentSwimLane) && kanbanId === currentId) {
      return;
    }
    console.log("API CALL MADE");
    axios.get(`api/kanban/getSwimLaneIn/${kanbanId}`).then((e) => {
      const arrOfLanes = e.data;
      const groupedObjects = arrOfLanes.reduce((group, obj) => {
        group[obj.id] = obj;
        return group;
      }, {});
      setCurrentSwimLane(groupedObjects);
      setLaneData(groupedObjects);
    });
  };

  const getKanban = (id) => {
    if (isEqual(currentKanban, currentKanban1) && id === currentId) {
      return;
    }
    axios.get(`api/kanban/getKanban/${id}`).then((e) => {
      setCurrentKanban(e.data);
      setCurrentKanban1(e.data);
    });
  };
  const onClickHandler = (event) => {
    const id = event.target.id;
    findSwimLanes(id);
    setCurrentId(id);
    getKanban(id);
  };
  return (
    <Link
      w={"full"}
      href="#"
      style={{ textDecoration: "none" }}
      _focus={{ boxShadow: "none" }}
    >
      <Flex
        align="center"
        p="4"
        borderRadius="lg"
        role="group"
        cursor="pointer"
        _hover={{
          bg: "cyan.400",
          color: "white",
        }}
        onClick={onClickHandler}
        {...rest}
      >
        {children}
      </Flex>
    </Link>
  );
};
export default NavItem;
