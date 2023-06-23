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

const LinkItems = [{ name: "web-page" }, { name: "blog-post" }];

const SidebarContent = ({ onClose, ...rest }) => {
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
      {LinkItems.map((link) => (
        <NavItem key={link.name} icon={link.icon}>
          {link.name}
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
