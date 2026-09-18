from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

class SummarizeRequest(BaseModel):
    text: str

@app.post("/summarize")
def summarize(req: SummarizeRequest):
    # 실제 프로젝트에서는 여기서 LLM을 호출합니다. 지금은 흉내만 냅니다.
    return {"summary": req.text[:15] + "...", "length": len(req.text)}