package adhocpoly.deep

trait Runner:
  def run() = println("Base Run")

given Runner with
  override def run() = println("Fast Run")


trait Holder:
  def doIt(using runner: Runner): Unit

given Holder with
  def doIt(using runner: Runner) =
    runner.run()

def ok(using holder: Holder) =
  holder.doIt

@main
def runIt() =
  ok

