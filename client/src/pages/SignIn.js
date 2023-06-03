import {
  Flex,
  VStack,
  Box,
  Heading,
  Link,
  InputGroup,
  InputLeftElement,
  Input,
  Stack,
  InputRightElement,
  Button,
  FormControl,
  FormHelperText,
} from "@chakra-ui/react";
import { FaUserAlt, FaLock } from "react-icons/fa";
import { useState, useEffect } from "react";
import { useLocalStorage } from "../hooks/useLocalStorage";

const SignIn = () => {
  const [showPassword, setShowPassword] = useState(false);
  const [jwt, setJwt] = useLocalStorage("", "jwt");
  useEffect(() => {
    if (!jwt) {
      const rqBody = { username: "andy", password: "password" };
      fetch("api/auth/login", {
        headers: {
          "Content-Type": "application/json",
        },
        method: "post",
        body: JSON.stringify(rqBody),
      })
        .then((response) => Promise.all([response.json(), response.headers]))
        .then(([body, headers]) => {
          setJwt(headers.get("authorization"));
          console.log(body);
        });
    }
  }, [jwt, setJwt]);

  console.log(jwt);
  const handleShowClick = () => {
    setShowPassword(!showPassword);
  };
  return (
    <Flex backgroundColor={"gray.200"}>
      <VStack>
        <Heading>GR.OOP</Heading>
        <Box>
          <Stack>
            <FormControl>
              <InputGroup>
                <InputLeftElement
                  pointerEvents="none"
                  children={<FaUserAlt />}
                />
                <Input type="text" placeholder="Username" />
              </InputGroup>
            </FormControl>
            <FormControl>
              <InputGroup>
                <InputLeftElement pointerEvents="none" children={<FaLock />} />
                <Input
                  type={showPassword ? "text" : "password"}
                  placeholder="Password"
                />
                <InputRightElement>
                  <Button h="1.75rem" size="sm" onClick={handleShowClick}>
                    {showPassword ? "Hide" : "Show"}
                  </Button>
                </InputRightElement>
              </InputGroup>
              <FormHelperText textAlign="right">
                <Link>forgot password?</Link>
              </FormHelperText>
            </FormControl>
            <Button>login</Button>
          </Stack>
        </Box>
        <Link color={"red.300"}>Create a new account</Link>
      </VStack>
    </Flex>
  );
};
export default SignIn;
