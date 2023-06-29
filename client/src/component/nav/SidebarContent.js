import NavItem from "./NavItem";
import {
  Box,
  Button,
  ButtonGroup,
  CloseButton,
  Divider,
  Flex,
  FormControl,
  FormLabel,
  IconButton,
  Input,
  Modal,
  ModalCloseButton,
  ModalContent,
  ModalOverlay,
  Stack,
  Text,
  useColorModeValue,
  useDisclosure,
} from "@chakra-ui/react";
import { AddIcon } from "@chakra-ui/icons";
import { useContext, useState } from "react";
import { GlobalKanbanStateContext } from "../../store/KanbanContext";
import axios from "axios";

const SidebarContent = ({ navOnClose, kanbans, ...rest }) => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [globalState, setGlobalState] = useContext(GlobalKanbanStateContext);
  const [kanbanTitle, setKanbanTitle] = useState("");

  const onTitleInputChange = (event) => {
    setKanbanTitle(event.target.value);
  };

  const createKanban = (event) => {
    event.preventDefault();
    let kanban = {};
    axios
      .post("api/kanban/createKanban", {
        //owner is temporarily hard coded
        owner_id: 1,
        title: kanbanTitle,
      })
      .then((res) => {
        kanban = res.data;
        console.log(kanban);
      })
      .then(() => {
        setGlobalState((prev) => [...prev, kanban]);
        onClose();
      });
  };
  return (
    <>
      <Box
        transition="3s ease"
        bg={useColorModeValue("white", "gray.900")}
        borderRight="1px"
        borderRightColor={useColorModeValue("gray.200", "gray.700")}
        w={{ base: "full", md: 60 }}
        pos="fixed"
        h="full"
        {...rest}
      >
        <Flex h="20" alignItems="center" mx="8" justifyContent="space-between">
          <Text fontSize="2xl" fontFamily="monospace" fontWeight="bold">
            GROOP
          </Text>
          <CloseButton
            display={{ base: "flex", md: "none" }}
            onClick={navOnClose}
          />
        </Flex>
        {globalState.map((kanban) => (
          <NavItem id={kanban.id} key={kanban.id}>
            {kanban.title}
          </NavItem>
        ))}
        <Divider />
        <ButtonGroup
          onClick={onOpen}
          mt={4}
          mx={4}
          size="sm"
          isAttached
          variant="outline"
        >
          <Button p={4}>create</Button>
          <IconButton p={4} aria-label="new project" icon={<AddIcon />} />
        </ButtonGroup>
      </Box>

      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalCloseButton />
          <Box
            rounded={"lg"}
            bg={useColorModeValue("white", "gray.700")}
            boxShadow={"lg"}
            p={8}
          >
            <form onSubmit={createKanban}>
              <Stack spacing={4}>
                <FormControl id="name">
                  <FormLabel>Project name</FormLabel>
                  <Input onChange={onTitleInputChange} required type="text" />
                </FormControl>
                <Stack spacing={10}>
                  <Button
                    type={"submit"}
                    bg={"blue.400"}
                    color={"white"}
                    _hover={{
                      bg: "blue.500",
                    }}
                  >
                    CREATE
                  </Button>
                </Stack>
              </Stack>
            </form>
          </Box>
        </ModalContent>
      </Modal>
    </>
  );
};
export default SidebarContent;
