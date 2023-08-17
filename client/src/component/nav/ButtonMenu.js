import { RiFileShredLine, RiPencilFill, RiUser3Fill } from "react-icons/ri";
import {
  Button,
  Flex,
  Popover,
  PopoverArrow,
  PopoverBody,
  PopoverContent,
  PopoverTrigger,
  Stack,
} from "@chakra-ui/react";
import axios from "axios";
import { useLocalStorage } from "../../hooks/useLocalStorage";
import { useContext } from "react";
import { GlobalCurrentKanbanStateContext } from "../../store/CurrentKanbanContext";
import { GlobalSwimLaneStateContext } from "../../store/SwimLaneConetext";
import { GlobalCurrentUserStateContext } from "../../store/CurrentUserContext";

const ButtonMenu = ({ id, children }) => {
  const [laneData, setLaneData] = useLocalStorage({}, "swimLane");
  const [localKanban, setLocalKanban] = useLocalStorage({}, "currentKanban");
  const [currentKanban, setCurrentKanban] = useContext(
    GlobalCurrentKanbanStateContext
  );
  const [currentSwimLane, setCurrentSwimLane] = useContext(
    GlobalSwimLaneStateContext
  );
  const [currentUser] = useContext(GlobalCurrentUserStateContext);

  const delKanban = () => {
    console.log(currentKanban);
    if (currentKanban.owner_id === currentUser) {
      axios.delete(`/api/kanban/${id}`).then((res) => {
        console.log(res);
        console.log(laneData);
      });
      document.getElementById(id).remove();
      setLaneData({});
      setCurrentKanban({});
      setCurrentSwimLane({});
      setLocalKanban({});
    }
  };
  return (
    <Flex
      align="center"
      _hover={{
        bg: "cyan.400",
      }}
      mx={4}
      borderRadius="lg"
      justifyContent="center"
    >
      <Popover placement="bottom" isLazy>
        <PopoverTrigger>{children}</PopoverTrigger>
        <PopoverContent w="fit-content" _focus={{ boxShadow: "none" }}>
          <PopoverArrow />
          <PopoverBody>
            <Stack>
              <Button
                w="194px"
                variant="ghost"
                rightIcon={<RiUser3Fill />}
                justifyContent="space-between"
                fontWeight="normal"
                fontSize="sm"
              >
                manage users
              </Button>
              <Button
                w="194px"
                variant="ghost"
                rightIcon={<RiPencilFill />}
                justifyContent="space-between"
                fontWeight="normal"
                colorScheme="normal"
                fontSize="sm"
              >
                edit project
              </Button>
              <Button
                w="194px"
                variant="ghost"
                rightIcon={<RiFileShredLine />}
                justifyContent="space-between"
                fontWeight="normal"
                colorScheme="red"
                fontSize="sm"
                onClick={delKanban}
              >
                Delete
              </Button>
            </Stack>
          </PopoverBody>
        </PopoverContent>
      </Popover>
    </Flex>
  );
};
export default ButtonMenu;
