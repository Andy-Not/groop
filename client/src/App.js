import { Button, Input } from "@chakra-ui/react";
import { useEffect, useState } from "react";
function App() {
  const [code, setCode] = useState("");
  const [key, setKey] = useState("");

  useEffect(() => {
    setCode(randomId());
  }, []);

  console.log(key);
  const randomId = function (length = 6) {
    return (
      "#" +
      Math.random()
        .toString(36)
        .substring(2, length + 2)
    );
  };

  return (
    <>
      {code}
      <Input placeholder="CODE" />
      <Button variant={"solid"}>login</Button>
      <Button
        variant={"solid"}
        onClick={() => {
          setCode(randomId());
        }}
      >
        generate
      </Button>
    </>
  );
}

export default App;
