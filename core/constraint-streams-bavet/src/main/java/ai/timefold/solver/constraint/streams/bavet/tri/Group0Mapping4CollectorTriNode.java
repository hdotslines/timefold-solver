package ai.timefold.solver.constraint.streams.bavet.tri;

import ai.timefold.solver.constraint.streams.bavet.common.TupleLifecycle;
import ai.timefold.solver.constraint.streams.bavet.quad.QuadTuple;
import ai.timefold.solver.constraint.streams.bavet.quad.QuadTupleImpl;
import ai.timefold.solver.core.api.score.stream.ConstraintCollectors;
import ai.timefold.solver.core.api.score.stream.tri.TriConstraintCollector;
import ai.timefold.solver.core.config.solver.EnvironmentMode;
import ai.timefold.solver.core.impl.util.Quadruple;

final class Group0Mapping4CollectorTriNode<OldA, OldB, OldC, A, B, C, D, ResultContainerA_, ResultContainerB_, ResultContainerC_, ResultContainerD_>
        extends
        AbstractGroupTriNode<OldA, OldB, OldC, QuadTuple<A, B, C, D>, QuadTupleImpl<A, B, C, D>, Void, Object, Quadruple<A, B, C, D>> {

    private final int outputStoreSize;

    public Group0Mapping4CollectorTriNode(int groupStoreIndex, int undoStoreIndex,
            TriConstraintCollector<OldA, OldB, OldC, ResultContainerA_, A> collectorA,
            TriConstraintCollector<OldA, OldB, OldC, ResultContainerB_, B> collectorB,
            TriConstraintCollector<OldA, OldB, OldC, ResultContainerC_, C> collectorC,
            TriConstraintCollector<OldA, OldB, OldC, ResultContainerD_, D> collectorD,
            TupleLifecycle<QuadTuple<A, B, C, D>> nextNodesTupleLifecycle, int outputStoreSize,
            EnvironmentMode environmentMode) {
        super(groupStoreIndex, undoStoreIndex, null, mergeCollectors(collectorA, collectorB, collectorC, collectorD),
                nextNodesTupleLifecycle, environmentMode);
        this.outputStoreSize = outputStoreSize;
    }

    static <OldA, OldB, OldC, A, B, C, D, ResultContainerA_, ResultContainerB_, ResultContainerC_, ResultContainerD_>
            TriConstraintCollector<OldA, OldB, OldC, Object, Quadruple<A, B, C, D>> mergeCollectors(
                    TriConstraintCollector<OldA, OldB, OldC, ResultContainerA_, A> collectorA,
                    TriConstraintCollector<OldA, OldB, OldC, ResultContainerB_, B> collectorB,
                    TriConstraintCollector<OldA, OldB, OldC, ResultContainerC_, C> collectorC,
                    TriConstraintCollector<OldA, OldB, OldC, ResultContainerD_, D> collectorD) {
        return (TriConstraintCollector<OldA, OldB, OldC, Object, Quadruple<A, B, C, D>>) ConstraintCollectors.compose(
                collectorA, collectorB, collectorC, collectorD, Quadruple::of);
    }

    @Override
    protected QuadTupleImpl<A, B, C, D> createOutTuple(Void groupKey) {
        return new QuadTupleImpl<>(null, null, null, null, outputStoreSize);
    }

    @Override
    protected void updateOutTupleToResult(QuadTupleImpl<A, B, C, D> outTuple, Quadruple<A, B, C, D> result) {
        outTuple.factA = result.getA();
        outTuple.factB = result.getB();
        outTuple.factC = result.getC();
        outTuple.factD = result.getD();
    }

}