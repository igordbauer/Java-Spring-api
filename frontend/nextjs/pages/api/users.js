import { connectToDatabase } from "../../src/util/dbAcess";

export default async function handler(req, res) {
  const client = await connectToDatabase();
  if (req.method === "POST") {
    const data = req.body;
    const result = await client.db().collection("users").insertOne(data);

    client.close();
    res.status(201).json(result);
  }
  if (req.method === "GET") {
    const result = await client.db().collection("users").find({}).toArray();

    res.status(201).json(result);
    client.close();
  }
}
