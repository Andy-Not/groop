import NavItem from "./NavItem";
import {
  Box,
  Button,
  ButtonGroup,
  CloseButton,
  Divider,
  Flex,
  IconButton,
  Text,
  useColorModeValue,
} from "@chakra-ui/react";
import { AddIcon } from "@chakra-ui/icons";
import { useContext } from "react";
import { GlobalKanbanStateContext } from "../../store/KanbanContext";

const SidebarContent = ({ onClose, kanbans, ...rest }) => {
  const [globalState] = useContext(GlobalKanbanStateContext);
  return (
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
        <CloseButton display={{ base: "flex", md: "none" }} onClick={onClose} />
      </Flex>
      {globalState.map((kanban) => (
        <NavItem id={kanban.id} key={kanban.id}>
          {kanban.title}
        </NavItem>
      ))}
      <Divider />
      <ButtonGroup mt={4} mx={4} size="sm" isAttached variant="outline">
        <Button p={4}>create</Button>
        <IconButton p={4} aria-label="new project" icon={<AddIcon />} />
      </ButtonGroup>
    </Box>
  );
};
export default SidebarContent;
