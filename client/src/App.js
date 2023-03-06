import { Button, Input } from "@chakra-ui/react";
import { useEffect, useState } from "react";
function App() {
  const [code, setCode] = useState("");

  useEffect(() => {
    setCode(randomId());
  }, []);

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
