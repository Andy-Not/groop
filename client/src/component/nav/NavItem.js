import { Flex, Link } from "@chakra-ui/react";
import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { GlobalSwimLaneStateContext } from "../../store/SwimLaneConetext";

const NavItem = ({ children, ...rest }) => {
  const [currentSwimLane, setCurrentSwimLane] = useContext(
    GlobalSwimLaneStateContext
  );
  const [prevState, setPrevState] = useState("");

  function isEqual(obj1, obj2) {
    return JSON.stringify(obj1) === JSON.stringify(obj2);
  }

  const findSwimLanes = (kanbanId) => {
    if (isEqual(currentSwimLane, prevState)) {
      return;
    }
    axios.get(`api/kanban/getSwimLaneIn/${kanbanId}`).then((e) => {
      console.log("CALL MADE IN NavItem.js");
      const arrOfLanes = e.data;
      const groupedObjects = arrOfLanes.reduce((group, obj) => {
        group[obj.id] = obj;
        return group;
      }, {});
      setCurrentSwimLane(groupedObjects);
    });
    setPrevState(currentSwimLane);
  };

  const onClickHandler = (event) => {
    const id = event.target.id;
    findSwimLanes(id);
  };
  return (
    <Link
      href="#"
      style={{ textDecoration: "none" }}
      _focus={{ boxShadow: "none" }}
    >
      <Flex
        align="center"
        p="4"
        mx="4"
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
