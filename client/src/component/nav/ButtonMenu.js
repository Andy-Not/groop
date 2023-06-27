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
import { useState } from "react";

const ButtonMenu = ({ id, children }) => {
  const [laneData, setLaneData] = useLocalStorage({}, "swimLane");
  const [menuIsHidden, setMenuIsHidden] = useState(false);
  const delKanban = () => {
    axios.delete(`/api/kanban/${id}`).then((res) => {
      console.log(res);
      console.log(laneData);
    });
    setLaneData({});
    document.getElementById(id).remove();
    setMenuIsHidden(true);
  };
  return (
    <Flex
      display={menuIsHidden ? "none" : "flex"}
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
