import { Flex, Link } from "@chakra-ui/react";
import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { GlobalSwimLaneStateContext } from "../../store/SwimLaneConetext";

const NavItem = ({ children, ...rest }) => {
  const [currentSwimLane, setCurrentSwimLane] = useContext(
    GlobalSwimLaneStateContext
  );
  const [prevState, setPrevState] = useState("");

  const findSwimLanes = (kanbanId) => {
    axios.get(`api/kanban/getSwimLaneIn/${kanbanId}`).then((e) => {
      console.log(e.data);
      const arrOfLanes = e.data;
      const groupedObjects = arrOfLanes.reduce((group, obj) => {
        group[obj.id] = obj;
        return group;
      }, {});
      setCurrentSwimLane(groupedObjects);
    });
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
