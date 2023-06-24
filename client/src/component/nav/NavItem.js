import { Flex, Link } from "@chakra-ui/react";

const NavItem = ({ children, ...rest }) => {
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
        onClick={(event) => {
          console.log(event);
        }}
        {...rest}
      >
        {children}
      </Flex>
    </Link>
  );
};
export default NavItem;
