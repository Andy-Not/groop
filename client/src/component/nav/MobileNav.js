import {
  Avatar,
  AvatarGroup,
  Box,
  Button,
  Divider,
  Flex,
  HStack,
  IconButton,
  Menu,
  MenuButton,
  MenuDivider,
  MenuItem,
  MenuList,
  Text,
  useColorModeValue,
  VStack,
} from "@chakra-ui/react";
import { FiBell, FiChevronDown, FiMenu } from "react-icons/fi";
import ButtonMenu from "./ButtonMenu";
import { useContext } from "react";
import { GlobalCurrentKanbanStateContext } from "../../store/CurrentKanbanContext";
import { useLocalStorage } from "../../hooks/useLocalStorage";

const MobileNav = ({ onOpen, ...rest }) => {
  const [currentKanban] = useContext(GlobalCurrentKanbanStateContext);
  const [localKanban, setLocalKanban] = useLocalStorage({}, "currentKanban");

  return (
    <>
      <Flex
        ml={{ base: 0, md: 60 }}
        px={{ base: 4, md: 4 }}
        height="20"
        alignItems="center"
        bg={useColorModeValue("white", "gray.900")}
        borderBottomWidth="1px"
        borderBottomColor={useColorModeValue("gray.200", "gray.700")}
        justifyContent={{ base: "space-between", md: "flex-end" }}
        {...rest}
      >
        <IconButton
          display={{ base: "flex", md: "none" }}
          onClick={onOpen}
          variant="outline"
          aria-label="open menu"
          icon={<FiMenu />}
        />

        <Text
          display={{ base: "flex", md: "none" }}
          fontSize="2xl"
          fontFamily="monospace"
          fontWeight="bold"
        >
          GROOP
        </Text>

        <HStack spacing={{ base: "0", md: "6" }}>
          <IconButton
            size="lg"
            variant="ghost"
            aria-label="open menu"
            icon={<FiBell />}
          />
          <Flex alignItems={"center"}>
            <Menu>
              <MenuButton
                py={2}
                transition="all 0.3s"
                _focus={{ boxShadow: "none" }}
              >
                <HStack>
                  <Avatar
                    size={"sm"}
                    src={
                      "https://images.unsplash.com/photo-1570288685280-7802a8f8c4fa?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=928&q=80"
                    }
                  />
                  <VStack
                    display={{ base: "none", md: "flex" }}
                    alignItems="flex-start"
                    spacing="1px"
                    ml="2"
                  >
                    <Text fontSize="sm">username</Text>
                    <Text fontSize="xs" color="gray.600">
                      Admin
                    </Text>
                  </VStack>
                  <Box display={{ base: "none", md: "flex" }}>
                    <FiChevronDown />
                  </Box>
                </HStack>
              </MenuButton>
              <MenuList
                bg={useColorModeValue("white", "gray.900")}
                borderColor={useColorModeValue("gray.200", "gray.700")}
              >
                <MenuItem>Profile</MenuItem>
                <MenuItem>Settings</MenuItem>
                <MenuDivider />
                <MenuItem>Sign out</MenuItem>
              </MenuList>
            </Menu>
          </Flex>
        </HStack>
      </Flex>
      <Flex p={2} justifyContent={"flex-end"} bg={"white"}>
        <HStack
          display={`${
            Object.keys(currentKanban).length === 0 ? "none" : "flex"
          }`}
        >
          <Text fontWeight={"bold"}>{currentKanban.title}</Text>
          <Divider orientation={"vertical"} />
          <AvatarGroup size="md" max={1}>
            <Avatar name="Ryan Florence" src="https://bit.ly/ryan-florence" />
            <Avatar name="Segun Adebayo" src="https://bit.ly/sage-adebayo" />
          </AvatarGroup>
          <Divider orientation={"vertical"} />
          <ButtonMenu id={currentKanban.id}>
            <Button>settings</Button>
          </ButtonMenu>
        </HStack>
      </Flex>
    </>
  );
};
export default MobileNav;
